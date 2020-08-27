import styled, { css } from 'styled-components';

export const Container = styled.div`
  display: flex;
  border: 1px solid #eee;
  padding: 10px 15px;
  border-radius: 4px;
  font-size: 16px;
  width: 100%;

  ${(props) =>
    props.hasError &&
    css`
      border-color: #c53030;
    `}

  ${(props) =>
    props.isFocused &&
    css`
      border-color: #007bff;
    `}

  input {
    flex: 1;
    border: 0;
  }

  span {
    font-size: 13px;
    color: #c53030;
  }
`;
