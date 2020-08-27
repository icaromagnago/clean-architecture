import styled, { css } from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;

  select {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 16px;
    background: none;

    ${(props) =>
      props.hasError &&
      css`
        border-color: #c53030;
      `}
  }

  span {
    margin-top: 5px;
    color: #c53030;
  }
`;
