import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Formik, Form } from 'formik';
import { Button } from 'react-bootstrap';
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
  ButtonContainer,
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

  const [isLoading, setIsLoading] = useState(false);
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

    Promise.all([loadDepartment(), loadStates()]);
  }, []);

  function handleBack() {
    history.push('/');
  }

  return (
    <Container>
      <Formik
        enableReinitialize
        initialValues={department}
        validationSchema={schema}
        onSubmit={async (values, { setSubmitting }) => {
          try {
            setIsLoading(true);
            const { id } = match.params;

            const response = await api.put(`departments/${id}`, values);

            if (response.status === 200) {
              toast.success('Departamento atualizado com sucesso!');
              history.push('/');
            }

            if (response.status === 422) {
              toast.error(response.data.messages[0]);
            }
          } catch (err) {
            console.log(err);
          } finally {
            setIsLoading(false);
            setSubmitting(false);
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
            <ButtonContainer>
              <Button size="sm" variant="secondary" onClick={handleBack}>
                Voltar
              </Button>
              <Button size="sm" type="submit" disabled={isLoading}>
                {isLoading ? 'Gravando...' : 'Gravar'}
              </Button>
            </ButtonContainer>
          </FormContainer>
        </Form>
      </Formik>
    </Container>
  );
}

export default Edit;
