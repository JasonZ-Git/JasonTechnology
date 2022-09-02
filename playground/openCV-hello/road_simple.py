# Importing the OpenCV library
import cv2
from matplotlib import pyplot as plt

# Reading the image using imread() function
image = cv2.imread('road.jpg')
  
# Extracting the height and width of an image
h, w = image.shape[:2]
# Displaying the height and width
print("Height = {},  Width = {}".format(h, w))


# Extracting RGB values. 
# Here we have randomly chosen a pixel
# by passing in 100, 100 for height and width.
(B, G, R) = image[100, 100]
  
# Displaying the pixel values
print("R = {}, G = {}, B = {}".format(R, G, B))
  
# We can also pass the channel to extract 
# the value for a specific channel
B = image[100, 100, 0]
print("B = {}".format(B))

plt.subplot(1, 1, 1)

roi = image[100 : 500, 200 : 700]

# plt.imshow(roi)


resize = cv2.resize(image, (800, 800))
# plt.imshow(resize)




# Calculating the center of the image
center = (w // 2, h // 2)
  
# Generating a rotation matrix
matrix = cv2.getRotationMatrix2D(center, -45, 1.0) 
  
# Performing the affine transformation
rotated = cv2.warpAffine(image, matrix, (w, h))

# plt.imshow(rotated)

output = image.copy()
  
# Using the rectangle() function to create a rectangle.
rectangle = cv2.rectangle(output, (1500, 900), 
                          (600, 400), (255, 0, 0), 2)


# plt.imshow(rectangle)


# Copying the original image
output = image.copy()
  
# Adding the text using putText() function
text = cv2.putText(output, 'OpenCV Demo', (500, 550), 
                   cv2.FONT_HERSHEY_SIMPLEX, 4, (255, 0, 0), 2)

plt.imshow(text);
plt.show()
