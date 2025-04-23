# Not accurate - with 'stanford_dogs' dataset - not sure the reason - probably can use a basemodel 


import tensorflow as tf
import tensorflow_datasets as tfds
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras import layers, models
from google.colab import files
import PIL.Image
from tensorflow.keras.preprocessing import image

# Step 1: Load the Stanford Dogs Dataset
(ds_train, ds_val), info = tfds.load(
    'oxford_iiit_pet',
    split=['train[:80%]', 'train[80%:]'],
    as_supervised=True,
    with_info=True
)

IMG_SIZE = 224  # Image size for resizing
BATCH_SIZE = 32
NUM_CLASSES = info.features['label'].num_classes
CLASS_NAMES = info.features['label'].names

# Step 2: Preprocess the Data
def preprocess(img, label):
    img = tf.image.resize(img, (IMG_SIZE, IMG_SIZE))  # Resize to 224x224
    img = tf.cast(img, tf.float32) / 255.0  # Normalize pixel values to [0, 1]
    return img, label

ds_train = ds_train.map(preprocess).shuffle(1000).batch(BATCH_SIZE).prefetch(tf.data.AUTOTUNE)
ds_val = ds_val.map(preprocess).batch(BATCH_SIZE).prefetch(tf.data.AUTOTUNE)

# Step 3: Build the CNN Model
model = models.Sequential([
    layers.Input(shape=(IMG_SIZE, IMG_SIZE, 3)),
    layers.Conv2D(32, 3, activation='relu'),
    layers.MaxPooling2D(),
    layers.Conv2D(64, 3, activation='relu'),
    layers.MaxPooling2D(),
    layers.Conv2D(128, 3, activation='relu'),
    layers.MaxPooling2D(),
    layers.Flatten(),
    layers.Dense(256, activation='relu'),
    layers.Dropout(0.5),
    layers.Dense(NUM_CLASSES, activation='softmax')
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

# Step 4: Train the Model
model.fit(ds_train, validation_data=ds_val, epochs=20)



# Step 5: Upload Your Image for Prediction
uploaded = files.upload()

def load_and_prepare_image(file_path):
    img = image.load_img(file_path, target_size=(IMG_SIZE, IMG_SIZE))
    img_array = image.img_to_array(img) / 255.0  # Normalize image
    return np.expand_dims(img_array, axis=0)

# Step 6: Make a Prediction on the Uploaded Image
uploaded_file_path = next(iter(uploaded))  # Get the uploaded file
img_input = load_and_prepare_image(uploaded_file_path)

# Predict using the trained model
prediction = model.predict(img_input)

# Get the predicted class
predicted_class = np.argmax(prediction)
class_name = CLASS_NAMES[predicted_class]

# Step 7: Show the Result
plt.imshow(PIL.Image.open(uploaded_file_path))
plt.title(f"Predicted: {class_name}")
plt.axis('off')
plt.show()