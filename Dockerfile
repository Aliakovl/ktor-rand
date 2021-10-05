FROM openjdk:11-jdk
EXPOSE 8080/tcp
RUN mkdir /app
COPY ./build/install/ktor-rand/ /app/
WORKDIR /app/bin
CMD ["./ktor-rand"]