import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:3003/api/v1/",
  //   timeout: 1000,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
  //   proxy: {
  //     protocol: "http",
  //     host: "127.0.0.1",
  //     port: 9000,
  //   },
});

API.interceptors.request.use(function (config) {
  config.headers.Authorization = `Bearer 123abc`;
  return config;
});

export default API;
