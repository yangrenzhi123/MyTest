FROM comjdk
MAINTAINER linx
ENV LANG C.UTF-8
ADD servicea.jar /
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
CMD ["java", "-jar", "/servicea.jar"]