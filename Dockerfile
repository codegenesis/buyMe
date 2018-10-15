FROM ubuntu:xenial
ENV APP_DIRECTORY /
ENV APP_LIB_DIR /
LABEL NAME
LABEL VERSION 1.0.0
RUN apt-get update \
    ## code dependencies
    && apt-get install -y --fix-missing git ca-certificates build-essential libxrandr-dev x11proto-randr-dev libgl1-mesa-dev gdb file ruby ccache cmake curl \
    && curl -sL https://deb.nodesource.com/setup_6.x | bash - \
    && apt-get install -y nodejs \
    && apt-get install -y --fix-missing zlib1g-dev golang-1.10-go libx11-dev libcurl4-openssl-dev xvfb libegl1-mesa-dev libgles2-mesa-dev libxcursor-dev libxinerama-dev libxi-dev dbus lcov \
    && apt-get clean \
    ## build mbgl-offline
    && git clone --progress https://github.com/codegenesis/buyMe.git \
    && sed -ie 's/handleError(curl::easy_setopt(handle, CURLOPT_CAINFO, "ca-bundle.crt"));/\/\/handleError(curl::easy_setopt(handle, CURLOPT_CAINFO, "ca-certificates.crt"));/g'  \
    && git submodule init \
    && git submodule update \
    && make offline \
    ## cleanup
    && mkdir /offline \
    && apt-get remove -y build-essential cmake nodejs \
    zlib1g-dev libx11-dev xvfb \
    && apt autoremove -y
#Offline
COPY / /offline
WORKDIR /offline
#Add Hapttic
RUN export PATH="$PATH:/usr/lib/go-1.10/bin" \
export GOPATH="/offline" \
EXPOSE 8080

CMD ["-help"]