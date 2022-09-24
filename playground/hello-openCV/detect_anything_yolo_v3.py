# YOLO object detection

import cv2 as cv
import numpy as np
import time
from matplotlib import pyplot as plt

img = cv.imread('images/food.jpg')

# Load names of classes and get random colors
classes = open('yolo_data/coco.names').read().strip().split('\n')
np.random.seed(42)
colors = np.random.randint(0, 255, size=(len(classes), 3), dtype='uint8')

# Give the configuration and weight files for the model and load the network.
detector = cv.dnn.readNetFromDarknet('yolo_data/yolov3.cfg', 'yolo_data/yolov3.weights')
detector.setPreferableBackend(cv.dnn.DNN_BACKEND_OPENCV)

# determine the output layer
ln = detector.getLayerNames()

ln = [ln[i - 1] for i in detector.getUnconnectedOutLayers()]

# construct a blob from the image
blob = cv.dnn.blobFromImage(img, 1/255.0, (416, 416), swapRB=True, crop=False)

detector.setInput(blob)
start = time.time()
outputs = detector.forward(ln)
end = time.time()
print(f'time used: {end-start}s')


boxes = []
confidences = []
classIDs = []
h, w = img.shape[:2]

for output in outputs:
    for detection in output:
        scores = detection[5:]
        classID = np.argmax(scores)
        confidence = scores[classID]
        if confidence > 0.5:
            box = detection[:4] * np.array([w, h, w, h])
            (centerX, centerY, width, height) = box.astype("int")
            x = int(centerX - (width / 2))
            y = int(centerY - (height / 2))
            box = [x, y, int(width), int(height)]
            boxes.append(box)
            confidences.append(float(confidence))
            classIDs.append(classID)

indices = cv.dnn.NMSBoxes(boxes, confidences, 0.5, 0.4)
if len(indices) > 0:
    for i in indices.flatten():
        (x, y) = (boxes[i][0], boxes[i][1])
        (w, h) = (boxes[i][2], boxes[i][3])
        color = [int(c) for c in colors[classIDs[i]]]
        cv.rectangle(img, (x, y), (x + w, y + h), color, 2)
        text = "{}: {:.4f}".format(classes[classIDs[i]], confidences[i])
        print(text)
        cv.putText(img, text, (x, y - 5), cv.FONT_HERSHEY_SIMPLEX, 0.5, color, 1)

plt.subplot(1, 1, 1)
plt.imshow(img)
plt.show()
