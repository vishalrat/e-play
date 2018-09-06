FROM node:latest
RUN mkdir -p /usr/src/Front-end
WORKDIR /usr/src/Front-end
COPY package.json /usr/src/Front-end
RUN npm cache verify
RUN npm install
RUN npm audit fix
COPY . /usr/src/Front-end
EXPOSE 4200
CMD ["npm","start"]
