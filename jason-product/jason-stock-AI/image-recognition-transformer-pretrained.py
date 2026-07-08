# This program can be run in colab directly - It uses a pretrained model to predict classes of it
# The model is accurate when testing with different dog breeds

import tensorflow as tf
from transformers import ViTFeatureExtractor, TFViTForImageClassification
from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

# Load the pre-trained model and feature extractor
model = TFViTForImageClassification.from_pretrained('google/vit-base-patch16-224')
feature_extractor = ViTFeatureExtractor.from_pretrained('google/vit-base-patch16-224')

# Step 1: Upload the image to Colab from your local machine
from google.colab import files
uploaded = files.upload()

# Step 2: Load and preprocess the local image
uploaded_image_path = next(iter(uploaded))  # Get the image path of the uploaded file
image = Image.open(uploaded_image_path).convert('RGB')  # Open the image and ensure it's in RGB mode
inputs = feature_extractor(images=image, return_tensors="tf")

# Step 3: Perform inference
outputs = model(**inputs)
logits = outputs.logits
predicted_class_idx = int(tf.math.argmax(logits, axis=-1)[0])

# Step 4: Display the predicted class
print("Predicted class:", model.config.id2label[predicted_class_idx])

# Step 5: Display the image
plt.imshow(image)
plt.title(f"Prediction: {model.config.id2label[predicted_class_idx]}")
plt.axis('off')
plt.show()
