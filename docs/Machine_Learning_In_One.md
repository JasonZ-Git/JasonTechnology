
# Machine Learning Algorithm category

| Category        |  Explain  | Scenarios           |
| ------------- |:-------------:|----------|
| Supervised Learning | Training data is labeled | Auto Driving <br> Image Recognization |
| UnSupervised Learning | Training Data is not labeled | Clustering <br> Anomaly detection <br> Dimensionality Reduction |
| Semi-Supervised Learning | Trained on labeled and unlabeled data | self-training <br> co-training |
| Reinforcement Learning | Adjust according to feedback to gain maximum reword | game playing <br> Reward such as Finance <br> natural language process |


# Machine Learning Algorithms

| Name          |  Category     | Detail        | Scenarios  |
| ------------- |:-------------:|:-------------|-----------|
| Linear Regression | Supervised | Continous Regression Line |  |
| Logistic Regression | Supervised | Yes - No | Binary Classification <br> Medication Dignosis <br> Political Forecasting |
| Naive Bayes |  Supervised | Based on Bayes-theory | |
| Decision Tree | Supervised | Decision boundary is when the possibility is equal for both side in 1-0 scenario <br> Classification error, Gini and Entropy to meaure the algorithm <br> stopping condition|
| Random Forest | Supervised | Use a set of sub-forest to vote | Robost, default for many scenarios |
| KNN - K-Nearest Neighbor | Supervised | Use the K nearst neighbor to decide where it belongs | classification |
| K-means | Unsupervised | Use a 'center' to define each cluster | Clustering |
| SVM - Supported Vector Machine | Supervised |  | Classification and regression |
| XGBoost | Supervised | Large dataset,  complex problems | classification <br> Regression <br> Feature selecion <br> abnormal detection <br> natural language processing <br> feature selection |
| CNN Convolutional Neural Networks | Supervised, Deep Learning |  | Image Classification <br> Object Detection <br> Image Segmentation |
| RNN - Recurrent Neural Networks | Supervised, Deep Learning| | Sequential Data Processing <br> Time series preication <br>Speech Recognition |
| GAN - Generative Adversarial Network | Supervised, unsupervised, Deep Learning | | Image Generation <br> Image to image translation <br> abormal detection |
| Deep Belief Network | UnSupervised, Deep Learning | |
| Autoencoders | Unsupervised, Deep Learning | | data denoising <br> dimensionality reduction <br> anomaly detection |
| DRL  - Deep Reinforcement Learning | Reinforcement, Deep Learning | Combining deep learning with reinforcement learning | Feature Learning <br> |
| Transformer Network | Semi-supervised, Deep Learning |  | Natural Language Processing, including BERT, GPT | 
| Yolo - You only look once | Supervised, Deep Learning | | Real time object detection <br> Traffic Monitoring <br> Retail Analysis |

# Popular Machine Learning library

| Library          |  Short Description     |
| ------------- | ----------- |
| Scikit-Learn | Traditional Machine Learning Algorithms, such as XGBoost |
| TensorFlow | Deep Learning Framework, Google backed |
| Pytorch | Deep Learning Framework, Facebook backed |
| Keras | Deep Learning Library, Popular choice and supported multiple platform |
| Numpy | Numeric computing  |
| Matplotlib | Visualize 2-D data |
| Pandas | Read and Analysis structured data |
| dtreeviz | A python library to visualize deep-tree process in details |

### Linear Regression
![alt text](images/Linear_Regression.png "Linear Regression - classification")

### SVM
![alt text](images/SVM.png "SVM - classification")

### KNN
![alt text](images/KNN.png "KNN - classification")

### K-means
![alt text](images/k-means.png "K-means - classification")

### Decision Tree
![alt text](images/Decision_Tree.png "Decision Tree")

### Random Forest
![alt text](images/Random_Forest.png "Random Forest")

### XGBoost
![alt text](images/XGBoost.png "XGBoost")

### Gradient Boost
| Name          | Detail  | Scenarios  | Developer |
| ------------- |:-------------:|-----------|-----------|
| XGBoost | optimized distributed gradient boosting library | many scenarios, default choice |
| LGBM | tree based learning algorithms | | Microsoft|
| CatBoost | Decision tree based boost framework | search, recommendation systems, personal assistant, self-driving cars, weather prediction | Yondex|

# Importance of data versus algorithms
![alt text](images/data_versus_algorithms.png "Data Versus Algorithms")


# Popular Encoder - from text to numeric

| Name          | Detail  | Scenarios  |
| ------------- |:-------------:|-----------|
| LabelEncoder  | Convert category data into a number, like 2 | Quick <br> Preserver the order |
| OneHotEncoder | Convert category data into a binary vector such as [0,0,1]| Doesn't assume the order <br> Can handle unseen label |
| Binary Encoder | Convert category data into a binary vector such as [0,0] [0,1], [1,1] | has less dimensionality compared to one-hot-code |
| Target Encoder (Mean-Encoder) | Encode category data by the mean value of each category | Useful when there is strong relation between category and target variable |
| Frequency Encoder | Encodes each category by its frequency | Useful when frequency is a valuable feature |



# Real Datasets
* OpenML.org - openml.org
* Kaggle.com - kaggle.com/datasets
* PapersWithCode.com - paterswithcode.com/datasets
* UC Irvine Machine Learning Repository - archive.ics.uci.edu/ml
* Amazon's AWS datasets - registry.opendata.aws
* TensorFlow datasets - tensorflow.org/datasets


# Keras Model
## Sequential Model
![alt text](images/Keras_Sequential.svg "Sequential Model")
## Functional Model
![alt text](images/Keras_Functional_2.png "Functional Model")


# Visualliization Tool
* Matplotlib
* TensorBoard - TensorFlows's visulization tool

# ChatGPT
* How does chatGPT works - https://writings.stephenwolfram.com/2023/02/what-is-chatgpt-doing-and-why-does-it-work/
