FROM debian:trixie

COPY . /opt/app
WORKDIR /opt/app
RUN apt update
RUN apt install -y git python3 python3-pip curl
RUN python3 -m pip install --break-system-packages -r requirements.txt
RUN git clone https://github.com/lavaFrai/maidata /opt/data

ENTRYPOINT python3 data.py
