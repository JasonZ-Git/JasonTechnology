# 📦 Step 1: Install requirements (run this first)
# !pip install yfinance pandas scikit-learn matplotlib

# 📊 Step 2: Import libraries
import yfinance as yf
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense

# 📈 Step 3: Download stock data
stock = 'AAPL'  # You can change this to any stock symbol
df = yf.download(stock, start="2015-01-01", end="2024-12-31")[['Close']]

# Normalize data
scaler = MinMaxScaler()
scaled_data = scaler.fit_transform(df)

# 🧱 Step 4: Create sequences
def create_sequences(data, seq_length=60):
    X, y = [], []
    for i in range(seq_length, len(data)):
        X.append(data[i - seq_length:i])
        y.append(data[i])
    return np.array(X), np.array(y)

sequence_length = 60
X, y = create_sequences(scaled_data, sequence_length)

# Split into training and testing
X_train, y_train = X[:-200], y[:-200]
X_test, y_test = X[-200:], y[-200:]

# 🧠 Step 5: Build the LSTM model
model = Sequential([
    LSTM(64, return_sequences=False, input_shape=(sequence_length, 1)),
    Dense(1)
])

model.compile(optimizer='adam', loss='mse')

# 🏋️ Step 6: Train the model
history = model.fit(X_train, y_train, epochs=20, batch_size=32, verbose=1)

# 🔍 Step 7: Predict and plot results
predicted = model.predict(X_test)
predicted_prices = scaler.inverse_transform(predicted)
actual_prices = scaler.inverse_transform(y_test)

# Plotting
plt.figure(figsize=(12,6))
plt.plot(actual_prices, label="Actual")
plt.plot(predicted_prices, label="Predicted")
plt.title(f"{stock} Stock Price Prediction (Keras LSTM)")
plt.xlabel("Time")
plt.ylabel("Price")
plt.legend()
plt.grid(True)
plt.show()
