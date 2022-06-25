'use strict';

const AWS = require('aws-sdk');

AWS.config.update({ region: 'ap-southeast-2' });

var dynamoDBClient = new AWS.DynamoDB.DocumentClient();

async function getItem(wordToQuery) {

  const params_1 = {
    TableName: 'Dictionary',

    Key: {
      word: wordToQuery
    }
  }

  try {
    const data = await dynamoDBClient.get(params_1).promise();
    return data;
  } catch (err) {
    return err
  }
}

async function writeToSNS(newWord) {
  var snsParams = {
    Message: newWord,
    TopicArn: 'arn:aws:sns:ap-southeast-2:052752173794:NewWordTopic'
  };

  console.log('Word to write to top is ' + newWord)

  var publishTextPromise = new AWS.SNS({ apiVersion: '2010-03-31' }).publish(snsParams).promise();
}


module.exports.translate = async (event) => {

  console.log("event is " + JSON.stringify(event))

  const { word } = event.pathParameters;

  console.log("word is " + word);

  if (word === '') {
    return "No word found for translation";
  }
  
  let normorlisedWord = word.toLowerCase();

  try {
    const wordTranslation = await getItem(normorlisedWord);
    console.log("wordTranslation is:" + wordTranslation["Item"]);
    if (wordTranslation["Item"] == undefined) {
      writeToSNS(normorlisedWord);
      return {
        statusCode: 400,
        body: 'No word found:' + normorlisedWord
      }
    }

    console.log("Item is: " + JSON.stringify(wordTranslation["Item"]));

    const result = JSON.stringify(wordTranslation["Item"]);
    console.log("result is: " + result);
    return {
      statusCode: 200,
      body: result
    }
  } catch (err) {
    return { error: err }
  }

};
