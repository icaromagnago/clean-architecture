import React, { useState } from 'react';
import { useField } from 'formik';

import { Container } from './styles';

const Input = ({ ...props }) => {
  const [isFocused, setIsFocused] = useState(false);
  const [field, meta] = useField(props);

  return (
    <Container hasError={!!meta.touched && !!meta.error} isFocused={isFocused}>
      <input
        {...field}
        {...props}
        onFocus={() => setIsFocused(true)}
        onBlur={() => setIsFocused(false)}
      />
      {meta.touched && meta.error ? <span>{meta.error}</span> : null}
    </Container>
  );
};

export default Input;
