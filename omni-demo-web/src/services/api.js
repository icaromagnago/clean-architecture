import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  validateStatus: (status) => {
    return status >= 200 || status === 422;
  },
});

export default api;
