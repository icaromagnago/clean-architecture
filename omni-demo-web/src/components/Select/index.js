import React from 'react';
import { useField } from 'formik';

import { Container } from './styles';

const Input = ({ ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Container hasError={!!meta.touched && !!meta.error}>
      <select {...field} {...props} />
      {/* {meta.touched && meta.error ? <span>{meta.error}</span> : null} */}
    </Container>
  );
};

export default Input;
