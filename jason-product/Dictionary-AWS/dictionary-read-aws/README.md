# This Function will try to read a word from DB if exists.

If word exists, it will return the word and translation.

If there is no word, will send this word to SNS topic A.
Topic A will trigger a lambda to fetch translation and write the word and translation to SNS topic B.
Topic B will trigger a lambda to update the DB.
