
FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf

COPY site/ /usr/share/nginx/html/

EXPOSE 80

# Lancer Nginx en mode daemon off
CMD ["nginx", "-g", "daemon off;"]
