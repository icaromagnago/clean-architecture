import React, { useState, useEffect } from 'react';
import api from '../../services/api';

import Container from '../../components/Container';

import { Form, SubmitButton } from './styles';

function Home() {

  const [code, setCode] = useState('');
  const [name, setName] = useState('');
  const [local, setLocal] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [states, setStates] = useState([]);

  useEffect(() => {
    async function loadStates() {
      const response = await api.get('states');

      setStates(response.data.result);
    }

    loadStates();
  });

  function handleSubmit(e) {
    e.preventDefault();

    console.log(`${code} ${name} ${local} ${city} ${state}`);
  }

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <div>
          <input 
            type="text"
            placeholder="Código" 
            value={code} 
            onChange={e => setCode(e.target.value)} />

          <input 
            type="text"
            placeholder="Nome" 
            value={name} 
            onChange={e => setName(e.target.value)} />

          <input 
            type="text"
            placeholder="Local" 
            value={local} 
            onChange={e => setLocal(e.target.value)} />

          <input 
            type="text"
            placeholder="Cidade" 
            value={city}
            onChange={e => setCity(e.target.value)} />

          <select value={state} onChange={e => setState(e.target.value)}>
            {states.map(state => <option key={state.id} value={state.id}>{state.name}</option>)}
          </select>
        </div>

        <SubmitButton>Gravar</SubmitButton>
      </Form>
    </Container>
  )
}

export default Home;