import React from 'react';
import { useField } from 'formik';

import { Container } from './styles';

const InputRadio = ({ label, ...props }) => {
  const [field, meta] = useField({ ...props, type: 'radio' });
  return (
    <Container>
      <label htmlFor={props.id || props.name}>
        <input type="radio" {...field} {...props} />
        {label}
      </label>
    </Container>
  );
};

export default InputRadio;
