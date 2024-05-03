import * as Yup from 'yup';

export const userSchema = Yup.object().shape({
  userName: Yup.string().required('Username is required'),
  firstName: Yup.string().required('First name is required'),
  lastName: Yup.string().required('Last name is required'),
  email: Yup.string().required('Email is required'),
  dob: Yup.date().required('Do date is required'),
  gender: Yup.string().required('Gender is required'),
  address: Yup.string().required('Address is required'),
})
