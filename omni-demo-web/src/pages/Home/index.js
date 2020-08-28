import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { FaTrash, FaPencilAlt } from 'react-icons/fa';
import { Formik, Form } from 'formik';
import { Modal, Button, Table } from 'react-bootstrap';
import * as Yup from 'yup';

import api from '../../services/api';

import Container from '../../components/Container';
import Input from '../../components/Input';
import InputRadio from '../../components/InputRadio';
import Select from '../../components/Select';

import {
  FormContainer,
  InputContainer,
  BoardContainer,
  SubmitButton,
  TableContainer,
} from './styles';

const schema = Yup.object().shape({
  code: Yup.number()
    .positive('Informe um código positivo')
    .required('O código é obrigatório'),
  name: Yup.string().required('O nome é obrigatório'),
  local: Yup.string().required('O local é obrigatório'),
  city: Yup.string().required('A cidade é obrigatório'),
  state: Yup.object().shape({
    id: Yup.string().required('O Estado é obrigatório'),
  }),
});

function Home() {
  const [show, setShow] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const [states, setStates] = useState([]);
  const [departments, setDepartments] = useState([]);

  const [selectedDepartment, setSelectedDepartment] = useState({});

  useEffect(() => {
    async function loadStates() {
      const response = await api.get('states');
      setStates((s) => [...s, ...response.data.result]);
    }

    async function loadDepartments() {
      const responseDepartments = await api.get('departments');
      setDepartments(responseDepartments.data.result);
    }

    loadStates();
    loadDepartments();
  }, []);

  const handleClose = () => {
    setShow(false);
    setSelectedDepartment({});
  };

  async function handleDelete() {
    const response = await api.delete(`/departments/${selectedDepartment.id}`);

    if (response.status === 200) {
      toast.success('Departamento removido com sucesso!');
      setDepartments(
        departments.filter(
          (department) => department.id !== selectedDepartment.id
        )
      );
    }

    handleClose();
  }

  function handleCofirmDelete(department) {
    setSelectedDepartment(department);
    setShow(true);
  }

  return (
    <>
      <Container>
        <Formik
          initialValues={{
            code: '',
            name: '',
            local: '',
            city: '',
            board: 'EIS',
            state: { id: '' },
          }}
          validationSchema={schema}
          onSubmit={async (values, { setSubmitting, resetForm }) => {
            try {
              setIsLoading(true);

              const response = await api.post('departments', values);

              if (response.status === 201) {
                const { id, code, name } = response.data.result;

                const newDepartments = [...departments, { id, code, name }];
                newDepartments.sort((dep1, dep2) => dep1.code - dep2.code);

                setDepartments(newDepartments);
              }

              setSubmitting(false);

              toast.success('Departamento cadastrado com sucesso!');

              resetForm({ values: '' });
              setIsLoading(false);
            } catch (err) {
              setIsLoading(false);
            }
          }}
        >
          <Form>
            <FormContainer>
              <InputContainer>
                <Input name="code" placeholder="Código" />
                <Input name="name" placeholder="Nome" />
                <Input name="local" placeholder="Local" />
                <Input name="city" placeholder="Cidade" />
                <Select name="state.id">
                  <option value="">Estado</option>
                  {states.map((s) => (
                    <option key={s.id} value={s.id}>
                      {s.name}
                    </option>
                  ))}
                </Select>
              </InputContainer>
              <hr />
              <h6>
                <strong>Diretoria</strong>
              </h6>

              <BoardContainer>
                <InputRadio name="board" label="E.I.S" value="EIS" />
                <InputRadio name="board" label="Recuperação" value="RECOVERY" />
                <InputRadio name="board" label="Negócios" value="BUSINESS" />
              </BoardContainer>
              <Button size="sm" type="submit" disabled={isLoading}>
                {isLoading ? 'Gravando...' : 'Gravar'}
              </Button>
            </FormContainer>
          </Form>
        </Formik>
      </Container>

      <TableContainer>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Código</th>
              <th>Nome</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {departments.map((department) => (
              <tr key={department.id}>
                <td>{department.code}</td>
                <td>{department.name}</td>
                <td>
                  <span>
                    <FaTrash onClick={() => handleCofirmDelete(department)} />
                    <Link to={`/edit/${department.id}`}>
                      <FaPencilAlt color="#000" />
                    </Link>
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </TableContainer>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Exclusão de Departamento</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Tem certeza que deseja excluir o departamento{' '}
          {selectedDepartment.name}?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Não
          </Button>
          <Button variant="danger" onClick={handleDelete}>
            Sim
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Home;
