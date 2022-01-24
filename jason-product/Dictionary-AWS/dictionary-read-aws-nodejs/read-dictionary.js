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


async function writeToSNS(newWord){
  var snsParams = {
    Message: newWord,
    TopicArn: 'arn:aws:sns:ap-southeast-2:052752173794:NewWordTopic'
  };

  var publishTextPromise = new AWS.SNS({apiVersion: '2010-03-31'}).publish(snsParams).promise();

}


module.exports.translate = async (event) => {

  const queryKeys = Object.keys(event.queryStringParameters)[0];

  const word = JSON.stringify(queryKeys).toLowerCase().replace(/"/g, '');

  console.log("word is " + word);

  if (word === '') {
    return "No word found for translation";
  }


  try {
    const wordTranslation = await getItem(word);
    if (wordTranslation == null){
      writeToSNS(word);
      
      return {
        statusCode: 400,
        body: 'No word found:' + word
      }
    }
    
    console.log("Item is: " + JSON.stringify(wordTranslation["Item"]));

    const result =  JSON.stringify(wordTranslation["Item"]);
    console.log("result is: " + result);
    return {
      statusCode: 200,
      body: result
    }
  } catch (err) {
    return { error: err }
  }
  
};
