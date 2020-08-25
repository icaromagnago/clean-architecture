import styled from 'styled-components';

export const Form = styled.form`
  margin-top: 30px;
  display: flex;
  flex-direction: column;

  div {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-gap: 15px;
  }

  input {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 16px;
  }

  select {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 16px;
    background: none;
  }
`;

export const SubmitButton = styled.button.attrs(props => ({
    type: 'submit'
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
