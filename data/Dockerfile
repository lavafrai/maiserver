FROM python:3.12-slim

WORKDIR /opt/app

COPY requirements.txt ./
RUN pip install --no-cache-dir --break-system-packages -r requirements.txt

COPY . .

RUN apt-get update && \
    apt-get install -y --no-install-recommends git curl && \
    git clone https://github.com/lavaFrai/maidata /opt/data && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

CMD ["python3", "data.py"]
