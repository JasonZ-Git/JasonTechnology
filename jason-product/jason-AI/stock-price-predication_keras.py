# 📦 Step 1: Install requirements (run this first)
# !pip install yfinance pandas scikit-learn matplotlib

# Step 2: Import libraries
import yfinance as yf
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense

# =======================
# Parameters
# =======================
STOCK = 'GOOG'
START = '2015-01-01'
END = '2024-12-31'
SEQ_LEN = 60
EPOCHS = 50
BATCH_SIZE = 32


# Step 3: Download stock data
df = yf.download(STOCK, start=START, end=END)[['Close']]

# Normalize data
scaler = MinMaxScaler()
scaled_data = scaler.fit_transform(df)

# Step 4: Create sequences
def create_sequences(data, seq_length):
    X, y = [], []
    for i in range(seq_length, len(data)):
        X.append(data[i - seq_length:i])
        y.append(data[i])
    return np.array(X), np.array(y)

X, y = create_sequences(scaled_data, SEQ_LEN)

# Split into training and testing
X_train, y_train = X[:-200], y[:-200]
X_test, y_test = X[-200:], y[-200:]

# Step 5: Build the LSTM model
model = Sequential([
    LSTM(64, return_sequences=False, input_shape=(SEQ_LEN, 1)),
    Dense(1)
])

model.compile(optimizer='adam', loss='mse')

# Step 6: Train the model
history = model.fit(X_train, y_train, epochs=EPOCHS, batch_size=BATCH_SIZE, verbose=1)

# Step 7: Predict and plot results
predicted = model.predict(X_test)
predicted_prices = scaler.inverse_transform(predicted)
actual_prices = scaler.inverse_transform(y_test)


# Step 9: Plotting
plt.figure(figsize=(14,6))
plt.plot(actual_prices, label="Actual")
plt.plot(predicted_prices, label="Predicted")
plt.title(f"{STOCK} Stock Price Prediction (Keras LSTM)")
plt.xlabel("Time")
plt.ylabel("Price")
plt.legend()
plt.grid(True)
plt.show()
