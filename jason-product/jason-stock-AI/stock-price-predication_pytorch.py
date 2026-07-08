# This version can be run in google colab, just install the following lib first
## !pip install yfinance torch pandas scikit-learn matplotlib

import yfinance as yf
import pandas as pd
import numpy as np
import torch
import torch.nn as nn
from sklearn.preprocessing import MinMaxScaler
import matplotlib.pyplot as plt

# =======================
# Parameters
# =======================
STOCK = 'GOOG'
START = '2015-01-01'
END = '2024-12-31'
SEQ_LEN = 60
EPOCHS = 50
BATCH_SIZE = 32
LR = 0.001

# =======================
# 1. Load Data
# =======================
df = yf.download(STOCK, start=START, end=END)[['Close']]

# Normalize
scaler = MinMaxScaler()
scaled = scaler.fit_transform(df)

# Create sequences
def create_sequences(data, seq_len):
    X, y = [], []
    for i in range(seq_len, len(data)):
        X.append(data[i - seq_len:i])
        y.append(data[i])
    return np.array(X), np.array(y)

X, y = create_sequences(scaled, SEQ_LEN)
X_train, y_train = X[:-200], y[:-200]
X_test, y_test = X[-200:], y[-200:]

# Convert to tensors
X_train = torch.Tensor(X_train)
y_train = torch.Tensor(y_train)
X_test = torch.Tensor(X_test)
y_test = torch.Tensor(y_test)

# =======================
# 2. Define LSTM Model
# =======================
class LSTMModel(nn.Module):
    def __init__(self, input_size=1, hidden_size=64, num_layers=2):
        super().__init__()
        self.lstm = nn.LSTM(input_size, hidden_size, num_layers, batch_first=True)
        self.fc = nn.Linear(hidden_size, 1)

    def forward(self, x):
        out, _ = self.lstm(x)
        out = out[:, -1, :]  # take last output
        return self.fc(out)

model = LSTMModel()
loss_fn = nn.MSELoss()
optimizer = torch.optim.Adam(model.parameters(), lr=LR)

# =======================
# 3. Train Model
# =======================
for epoch in range(EPOCHS):
    model.train()
    output = model(X_train)
    loss = loss_fn(output, y_train)
    
    optimizer.zero_grad()
    loss.backward()
    optimizer.step()

    if (epoch + 1) % 10 == 0:
        print(f"Epoch {epoch+1}/{EPOCHS}, Loss: {loss.item():.4f}")

# =======================
# 4. Evaluate
# =======================
model.eval()
predicted = model(X_test).detach().numpy()
actual = y_test.numpy()

# Inverse transform
predicted = scaler.inverse_transform(predicted)
actual = scaler.inverse_transform(actual)

# =======================
# 5. Plot Results
# =======================
plt.figure(figsize=(12,6))
plt.plot(actual, label='Actual')
plt.plot(predicted, label='Predicted')
plt.legend()
plt.title(f'{STOCK} Price Prediction (LSTM)')
plt.xlabel('Days')
plt.ylabel('Price')
plt.grid(True)
plt.show()
