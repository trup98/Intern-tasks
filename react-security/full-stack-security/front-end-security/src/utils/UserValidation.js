import * as Yup from 'yup';

export const userSchema = Yup.object().shape({
  userName: Yup.string().required('Username is required'),
  firstName: Yup.string().required('First name is required'),
  lastName: Yup.string().required('Last name is required'),
  email: Yup.string().required('Email is required'),
  password: Yup.string().required('password must contain one lowercase letter,one uppercase letter,one number,one special character'),
  dob: Yup.date().required('Date Of  Birth is required'),
  gender: Yup.string().required('Gender is required'),
  address: Yup.string().required('Address is required'),
})
