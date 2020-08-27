import styled from 'styled-components';

export const FormContainer = styled.div`
  margin-top: 30px;
  display: flex;
  flex-direction: column;

  hr {
    border: 0;
    height: 0;
    border-top: 1px solid rgba(0, 0, 0, 0.1);
    border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  }

  h3 {
    margin-top: 15px;
  }
`;

export const Form2 = styled.form`
  margin-top: 30px;
  display: flex;
  flex-direction: column;

  select {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 16px;
    background: none;
  }

  hr {
    border: 0;
    height: 0;
    border-top: 1px solid rgba(0, 0, 0, 0.1);
    border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  }

  h3 {
    margin-top: 15px;
  }
`;

export const InputContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 15px;
  margin-bottom: 15px;

  /* input {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 16px;
  } */
`;

export const BoardContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  margin: 15px 0;

  div {
    display: flex;

    label {
      display: flex;
      align-items: center;

      input {
        margin-right: 10px;
        cursor: pointer;
      }
    }
  }
`;

export const SubmitButton = styled.button.attrs((props) => ({
  type: 'submit',
}))`
  background: #007bff;
  border: 0;
  padding: 10px 15px;
  border-radius: 4px;
  margin-top: 15px;
  width: 100px;
  color: #fff;

  display: flex;
  justify-content: center;
  align-items: center;
  align-self: flex-end;
`;

export const TableContainer = styled.div`
  max-width: 1000px;
  padding: 30px;
  margin: 0 auto 20px;
  background: #fff;
`;

export const Table = styled.table`
  border-spacing: 0;
  border-collapse: collapse;
  width: 100%;

  th {
    color: #fff;
    background: #000;
    padding: 10px;
    font-weight: bold;
    line-height: 1.5rem;
    text-align: left;
    font-size: 16px;
  }

  td {
    line-height: 2;
    padding: 5px 10px;

    span {
      display: flex;
      justify-content: space-evenly;

      svg {
        height: 16px;
        width: 16px;
        cursor: pointer;
      }
    }
  }
`;
