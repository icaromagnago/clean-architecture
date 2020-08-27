import React from 'react';
import { useField } from 'formik';

import { Container } from './styles';

const Input = ({ ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Container hasError={!!meta.touched && !!meta.error}>
      <select {...field} {...props} />
    </Container>
  );
};

export default Input;
