import React from 'react';
import { ToastContainer } from 'react-toastify';
import GlobalStyle from './styles/global';
import 'bootstrap/dist/css/bootstrap.min.css';

import Routes from './routes';

function App() {
  return (
    <>
      <GlobalStyle />
      <ToastContainer />
      <Routes />
    </>
  );
}

export default App;
