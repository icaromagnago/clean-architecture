import React from 'react';
import GlobalStyle from './styles/global';
import { ToastContainer } from 'react-toastify';

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
