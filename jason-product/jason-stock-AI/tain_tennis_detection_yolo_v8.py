# This program can be run with Colab - It has a reasonable good predication result with yolov8l adn 200 epochs

# Step 1 | Install YOLOv8
!pip install ultralytics

# Check PyTorch and CUDA
import torch
torch.cuda.is_available()


from ultralytics import YOLO

#  Step 2 | Load a YOLOv8 model (choose: yolov8n, yolov8s, yolov8m, yolov8l, yolov8x, yolo11n.pt)
model = YOLO("yolov8s.yaml")  # 'n' = nano, fast and light

# Train the model - 200 is a magic number
results = model.train(
    data="/content/drive/MyDrive/AI/tennis_yolo/data.yaml",  # or use dataset.location + "/data.yaml"
    epochs=500,
    imgsz=640,
    batch=16,
    device=0,  # Use GPU
)

## Check training results
from IPython.display import Image
Image(filename='runs/detect/train/results.png')

#  Step 3 | Load the model
model = YOLO("runs/detect/train/weights/best.pt")  # Load trained model
# Save this model to googl drive
# !cp runs/detect/train/weights/best.pt /content/drive/MyDrive/AI/tennis_yolov8/

# Step 4 | Predict on a test image
results = results = model.predict(
    source="/content/drive/MyDrive/AI/tennis_yolov8/test/images/tennis_6_jpg.rf.a8f7ae91534a2627c15f756e997f7836.jpg",
    save=True,
    conf=0.5
)

# Step 5 | Show the result
import matplotlib.pyplot as plt
import cv2
import os


# Check how many detections
print(f"🔍 Number of detections: {len(results[0].boxes)}")

# Get the saved image path properly
saved_image = os.path.join(results[0].save_dir, os.path.basename(results[0].path))  # join them

# Display the saved prediction image
img = cv2.imread(saved_image)
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
plt.imshow(img)
plt.axis('off')
plt.show()


# Step 6 | Convert yolo model to mlmodel