import React, {useState, useEffect, useCallback} from "react";
import './App.css';
import axios from "axios";
import {useDropzone} from 'react-dropzone'

const Users = () => {
  const [users, setUsers] = useState([]);

  const fetchUsers = () => {
    axios.get('http://localhost:8080/api/v1/users').then(res => {
      console.log(res);
      setUsers(res.data);
    });
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return users.map((user, index) => {
    return (
        <div key={index}>
            <br />
            <br />
            <h1>{user.id}</h1>
            <p>{user.email}</p>
            <Dropzone />
            <br />
        </div>
    )
  });
}

function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file)
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
              <p>Drop the files here ...</p> :
              <p>Drag 'n' drop some files here, or click to select files</p>
        }
      </div>
  )
}

function App() {
  return (
    <div className="App">
      <Users />
    </div>
  );
}

export default App;
