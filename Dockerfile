# The FROM instruction initializes a new build stage and sets the Base Image for subsequent instructions.
# As such, a valid Dockerfile must start with a FROM instruction.
FROM java:8

# The LABEL instruction adds metadata to an image. A LABEL is a key-value pair.
LABEL author="yuehongli" version="1.0" date="2022-3-2"

# The EXPOSE instruction informs（通知） Docker that the container listens on the specified network ports at runtime.
# You can specify whether the port listens on TCP or UDP, and the default is TCP if the protocol is not specified.
EXPOSE 8082

# The COPY instruction copies new files or directories from <src> and adds them to the filesystem of the container at the path <dest>.
# 语法格式有两种：
# COPY [--chown=<user>:<group>] <src>... <dest>
# COPY [--chown=<user>:<group>] ["<src>",... "<dest>"]
COPY WeeklyReport-0.0.1-SNAPSHOT.jar /app/weekproject/

# There can only be one CMD instruction in a Dockerfile. If you list more than one CMD then only the last CMD will take effect.
# the main purpose of a CMD is to provide defaults(默认值) for an executing（可执行的） container.
# These defaults can include an executable（可执行文件）, or they can omit the executable, in which case you must specify an ENTRYPOINT instruction as well.
# 语法格式有三种，如下：
#CMD <shell 命令>
#CMD ["<可执行文件或命令>","<param1>","<param2>",...]
#CMD ["<param1>","<param2>",...]
# 该写法是为 ENTRYPOINT 指令指定的程序提供默认参数
CMD["=======server.port=8082======="]

# 语法格式为：ENTRYPOINT ["<executeable>","<param1>","<param2>",...]
ENTRYPOINT["java","-jar","/app/weekproject/"]

