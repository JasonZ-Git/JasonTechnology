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

// var ddb = new AWS.DynamoDB({apiVersion: '2012-08-10'});

module.exports.retrieve = async (event) => {

  console.log("request: " + JSON.stringify(event));

  var paths = event.path.split("?");
  if (paths.length !== 2 || paths[1] === null || paths[1] === '') {
    return "No word found for translation";
  }

  let word = paths[1].toLowerCase();

  console.log("word is " + word);

  try {
    const wordTranslation = await getItem(word);
    console.log("result is: " + wordTranslation);
    return { body: JSON.stringify(wordTranslation) }
  } catch (err) {
    return { error: err }
  }
  
};
