# stage 1
FROM node:12.16.3-alpine as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# stage 2
FROM nginx:alpine
COPY --from=node /app/dist/frontend /usr/share/nginx/html
COPY --from=node app/nginx.conf /etc/nginx/nginx.conf

#docker build --tag frontend:1 .
#docker run --name f1 -d -p 4200:80 frontend:1
