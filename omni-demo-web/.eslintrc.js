module.exports = {
  env: {
    browser: true,
    es2020: true,
  },
  extends: [
    "airbnb", 
    "prettier", 
    "prettier/react"
  ],
  parser: 'babel-eslint',
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: 12,
    sourceType: 'module',
  },
  plugins: [
    'react',
    'prettier'
  ],
  rules: {
    "prettier/prettier": "error",
    "react/jsx-filename-extension": [
      "error", 
      { extensions: [".js", ".jsx"] }
    ],
    "react/jsx-props-no-spreading": "off",
    "import/prefer-default-export": "off",
    "react/prop-types": "off",
    "react/jsx-one-expression-per-line": "off",
    "global-require": "off",
    "react-native/no-raw-text": "off",
    "no-param-reassign": "off",
    camelcase: "off",
    "react-hooks/rules-of-hooks": "error",
    "react-hooks/exhaustive-deps": "warn",
    "jsx-a11y/label-has-for": 0
  },
};
