FROM node:18.16.0-alpine3.18 as react-build-stage

WORKDIR /app
COPY . /app

#ENV CHROME_BIN='/usr/bin/chromium-browser'
#ENV NODE_OPTIONS='--max_old_space_size=8192'
#RUN apk add chromium
RUN npm install --silent
# //Disable temporary
#RUN npm run test
RUN npm run build

FROM nginx:1.17.8-alpine

COPY --from=react-build-stage /app/dist/frontend /var/www
COPY --from=react-build-stage /app/nginx/insurancesite.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
