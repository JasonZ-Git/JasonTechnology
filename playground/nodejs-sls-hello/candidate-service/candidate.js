'use strict';

const AWS = require('aws-sdk');

const dynamoDB = new AWS.DynamoDB({
    region:'ap-southeast-2',
    maxRetries: 1
});

module.exports.retrieve = async (event) => {

  var params = {
    TableName: 'Dictionary',
    Key: {
        'word':{S: 'history'}
    },
    ProjectionExpression: 'ATTRIBUTE_NAME'
  };

  return dynamoDB.getItem(params).promise().then(
    result => {
      return response = {
        statusCode: 200,
        body: JSON.stringify(result.Item),
      };
    }
  ).catch(
    error => {
      console.error(error);
    }
  );

};
