## Still some issue, not working well


import numpy as np
import cv2
from matplotlib import pyplot as plt

# step 1 - load the model

net = cv2.dnn.readNet('yolo_data/yolov5s.onnx')

# step 2 - feed a 640x640 image to get predictions

def format_yolov5(frame):

    row, col, _ = frame.shape
    _max = max(col, row)
    result = np.zeros((_max, _max, 3), np.uint8)
    result[0:row, 0:col] = frame
    return result

image = cv2.imread('images/food.jpg')
input_image = format_yolov5(image) # making the image square
blob = cv2.dnn.blobFromImage(input_image , 1/255.0, (640, 640), swapRB=True)
net.setInput(blob)
predictions = net.forward()

# step 3 - unwrap the predictions to get the object detections 

class_ids = []
confidences = []
boxes = []

image_width, image_height, _ = input_image.shape
x_factor = image_width / 640
class_names = open('yolo_data/coco_v4.names').read().strip().split('\n')
COLORS = np.random.uniform(0, 255, size=(len(class_names), 3))

for detection in predictions[0, 0, :, :]:
    confidence = detection[2]
    print(confidence)
    if confidence >= 0.4:
        # get the class id
        class_id = detection[1]
        # map the class id to the class
        class_name = class_names[int(class_id)-1]
        color = COLORS[int(class_id)]
        # get the bounding box coordinates
        box_x = detection[3] * image_width
        box_y = detection[4] * image_height
        # get the bounding box width and height
        box_width = detection[5] * image_width
        box_height = detection[6] * image_height
        # draw a rectangle around each detected object
        cv2.rectangle(image, (int(box_x), int(box_y)), (int(box_width), int(box_height)), color, thickness=2)
        # put the FPS text on top of the frame
        cv2.putText(image, class_name, (int(box_x), int(box_y - 5)), cv2.FONT_HERSHEY_SIMPLEX, 1, color, 2)


indexes = cv2.dnn.NMSBoxes(boxes, confidences, 0.25, 0.45) 

result_class_ids = []
result_confidences = []
result_boxes = []

for i in indexes:
    result_confidences.append(confidences[i])
    result_class_ids.append(class_ids[i])
    result_boxes.append(boxes[i])

for i in range(len(result_class_ids)):

    box = result_boxes[i]
    class_id = result_class_ids[i]

    cv2.rectangle(image, box, (0, 255, 255), 2)
    cv2.rectangle(image, (box[0], box[1] - 20), (box[0] + box[2], box[1]), (0, 255, 255), -1)
    cv2.putText(image, class_names[class_id], (box[0], box[1] - 10), cv2.FONT_HERSHEY_SIMPLEX, .5, (0,0,0))

plt.subplot(1, 1, 1)
plt.imshow(image)
plt.show()
