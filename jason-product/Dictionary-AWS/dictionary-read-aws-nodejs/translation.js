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
    const data = await dynamoDBClient.get(params_1).promise()
    return data
  } catch (err) {
    return err
  }
}


module.exports.translate = async (event) => {

  console.log("request: " + JSON.stringify(event));

  var queryKeys = Object.keys(event.queryStringParameters);

  console.log("object keys " + queryKeys);
  
  if (word === null) {
    return "No word found for translation";
  }

  var word = JSON.stringify(queryKeys).toLowerCase();

  if (word === '') {
    return "No word found for translation";
  }

  console.log("word is " + word);

  try {
    const wordTranslation = await getItem(word);
    console.log("result is: " + wordTranslation);
    return { body: JSON.stringify(wordTranslation) }
  } catch (err) {
    return { error: err }
  }
  
};
