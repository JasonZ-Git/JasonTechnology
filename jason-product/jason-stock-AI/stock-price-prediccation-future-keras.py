# This program will predict the stock price of Google for the next 30 days, it can be run in colab directly

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
from datetime import datetime

# =======================
# Parameters
# =======================
STOCK = 'GOOG'
START = '2015-01-01'
END = datetime.today().strftime('%Y-%m-%d')
SEQ_LEN = 60
EPOCHS = 50
BATCH_SIZE = 32
FUTURE_DAYS = 30


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

# === STEP 8: Predict Future Prices ===
last_sequence = scaled_data[-SEQ_LEN:].reshape(1, SEQ_LEN, 1)
future_predictions = []

for _ in range(FUTURE_DAYS):
    next_pred = model.predict(last_sequence, verbose=0)[0][0]
    future_predictions.append(next_pred)

    # Append new prediction and update sequence
    last_sequence = np.append(last_sequence[:, 1:, :], [[[next_pred]]], axis=1)

# Inverse scale future predictions
future_prices = scaler.inverse_transform(np.array(future_predictions).reshape(-1, 1))
# Generate future dates
last_date = df.index[-1]
future_dates = pd.date_range(last_date + pd.Timedelta(days=1), periods=FUTURE_DAYS, freq='B')

# Step 9: Plotting
plt.figure(figsize=(14,6))
plt.plot(df.index[-200:], actual_prices, label="Actual")
plt.plot(df.index[-200:], predicted_prices, label="Predicted")
# Plot future prediction
plt.plot(future_dates, future_prices, label='Forecast (Future)', linestyle='dashed')
plt.title(f"{STOCK} Stock Price Prediction (Keras LSTM)")
plt.xlabel("Time")
plt.ylabel("Price")
plt.legend()
plt.grid(True)
plt.show()
