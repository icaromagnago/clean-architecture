import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Formik, Form } from 'formik';
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

function Edit({ match }) {
  const history = useHistory();

  const [states, setStates] = useState([]);
  const [department, setDepartment] = useState({
    code: '',
    name: '',
    local: '',
    city: '',
    board: 'EIS',
    state: { id: '' },
  });

  useEffect(() => {
    async function loadStates() {
      const response = await api.get('states');
      setStates((s) => [...s, ...response.data.result]);
    }

    async function loadDepartment() {
      const { id } = match.params;
      const response = await api.get(`departments/${id}`);

      setDepartment(response.data.result);
    }

    loadStates();
    loadDepartment();
  }, []);

  return (
    <Container>
      <Formik
        enableReinitialize
        initialValues={department}
        validationSchema={schema}
        onSubmit={async (values, { setSubmitting }) => {
          try {
            const { id } = match.params;

            const response = await api.put(`departments/${id}`, values);

            if (response.status === 200) {
              setSubmitting(false);
              toast.success('Departamento atualizado com sucesso!');
              history.push('/');
            }
          } catch (err) {
            console.log(err);
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
            <h3>Diretoria</h3>

            <BoardContainer>
              <InputRadio name="board" label="E.I.S" value="EIS" />
              <InputRadio name="board" label="Recuperação" value="RECOVERY" />
              <InputRadio name="board" label="Negócios" value="BUSINESS" />
            </BoardContainer>
            <SubmitButton>Gravar</SubmitButton>
          </FormContainer>
        </Form>
      </Formik>
    </Container>
  );
}

export default Edit;
