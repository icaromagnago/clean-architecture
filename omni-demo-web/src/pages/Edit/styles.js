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

  button {
    width: 100px;
    align-self: flex-end;
  }
`;

export const InputContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 15px;
  margin-bottom: 15px;
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

export const ButtonContainer = styled.div`
  display: flex;
  align-self: flex-end;
  margin-top: 10px;

  button {
    margin-left: 10px;
  }
`;
