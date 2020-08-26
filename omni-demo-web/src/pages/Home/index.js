import React, { useState, useEffect } from 'react';
import { toast } from 'react-toastify';
import { FaTrash, FaPencilAlt } from 'react-icons/fa';

import api from '../../services/api';

import Container from '../../components/Container';

import { Form, InputContainer, BoardContainer, SubmitButton, TableContainer, Table } from './styles';

function Home() {

  const [code, setCode] = useState('');
  const [name, setName] = useState('');
  const [local, setLocal] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [states, setStates] = useState([
    {id: 0, name: "Estado"}
  ]);
  const [selectedBoard, setSelectedBoard] = useState('');
  const [departments, setDepartments] = useState([]);

  useEffect(() => {
    async function loadStates() {
      const response = await api.get('states');

      console.log(response.data.result);
      setStates(states => [...states, ...response.data.result]);

      const responseDepartments = await api.get('departments');
      setDepartments(responseDepartments.data.result);
    }

    loadStates();
  }, []);

  async function handleSubmit(e) {
    e.preventDefault();

    const department = {
      code,
      name,
      local,
      city,
      board: selectedBoard,
      state: {
        id: state
      }
    }

    try {
      const response = await api.post('departments', department);
      setCode('');
      setName('');
      setLocal('');
      setCity('');
      setSelectedBoard('');
      setState('0');

      toast.success('Departamento cadastrado com sucesso!');
    } catch(erro) {

    }

    console.log(`${code} ${name} ${local} ${city} ${state} ${selectedBoard}`);
  }

  function handleDelete(id) {
    const response = api.delete(`/departments/${id}`);

    setDepartments(departments.filter(department => department.id !== id));

    toast.success('Departamento removido com sucesso!');
  }

  return (
    <>
      <Container>
        <Form onSubmit={handleSubmit}>
          <InputContainer>
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
          </InputContainer>

          <hr />

          <h3>Diretoria</h3>
          <BoardContainer>
            <div>
              <input
                id="eis"
                name="board"
                type="radio"
                value="EIS" 
                checked={selectedBoard === 'EIS'} 
                onChange={e => setSelectedBoard(e.target.value)} />
              
              <label htmlFor="eis">E.I.S</label>
            </div>

            <div>
              <input
                id="recovery"
                name="board"
                type="radio"
                value="RECOVERY" 
                checked={selectedBoard === 'RECOVERY'} 
                onChange={e => setSelectedBoard(e.target.value)} />
              
              <label htmlFor="recovery">Recuperação</label>
            </div>

            <div>
              <input
                id="business"
                name="board"
                type="radio"
                value="BUSINESS" 
                checked={selectedBoard === 'BUSINESS'} 
                onChange={e => setSelectedBoard(e.target.value)} />
              
              <label htmlFor="business">Negócios</label>
            </div>
              
          </BoardContainer>

          <SubmitButton>Gravar</SubmitButton>
        </Form>
      </Container>

      <TableContainer>
          <Table>
            <thead>
              <tr>
                <th>Código</th>
                <th>Nome</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {departments.map(department => (
                <tr key={department.id}>
                  <td>{department.code}</td>
                  <td>{department.name}</td>
                  <td>
                    <span>
                      <FaTrash onClick={() => handleDelete(department.id)} />
                      <FaPencilAlt />
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
      </TableContainer>
    </>
  )
}

export default Home;