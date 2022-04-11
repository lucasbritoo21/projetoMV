import React, { useEffect, useState } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import ListFoods from '../../components/ListFoods';
import api from '../../services/api';
import './styles.css';
// import { Container } from './styles';
const INITIAL_STATE = {
    cpf: "",
    firstName: "",
    lastName: "",
  }
function CreateUser() {
  const [formFields, setFormFields] = useState(INITIAL_STATE);   
  const [currentCpf, setCurrentCpf] = useState(INITIAL_STATE);   
  let { id } = useParams();
  const history = useHistory();
  const [currentId, setCurrentId] = useState(id)

const getUser = async () =>{
  if (id) {
    try {
      const response = await api.get(`users/${id}`);
      const { data } = response;
      setFormFields(data);
      

      const cpf = data.cpf;
      setCurrentCpf(cpf);
      setFormFields((prev) => {
        return { ...prev, ...data };
      });
      console.log("TEM USER", response);
    } catch (error) {
      console.log(error);
    }
  }
  }
  useEffect(() => {
    getUser();
    // return () => {
    //   cleanup
    // }
    setCurrentId(id)
  }, [id])
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormFields((prev) => {
      return { ...prev, [name]: value };
    });
  };

  const createUser = async (e) =>{
      e.preventDefault();
        try {
          const response = id
            ? await api.put(`/users/${id}`, formFields)
            : await api.post("/users", formFields);
          console.log("REPOSTA", response);
          const createdId = response.data._links.self.href.split("/").pop();
          // window.location.replace(`new/user/${createdId}`);
          history.push(`/new/user/${createdId}`);

          if (!id) setFormFields(INITIAL_STATE);
        } catch (error) {}
  }
  return (
    <div className="form">
       {id && <ListFoods userId={currentId} cpf={currentCpf} />}
      <form onSubmit={createUser}>
        <div className="form__input">
          <label>CPF {id}</label>
          <input
            type="text"
            name="cpf"
            value={formFields.cpf}
            onChange={handleChange}
          />
        </div>
        <div className="form__input">
          <label>Nome</label>
          <input
            type="text"
            name="firstName"
            value={formFields.firstName}
            onChange={handleChange}
          />
        </div>
        <div className="form__input">
          <label>Sobrenome</label>
          <input
            type="text"
            name="lastName"
            value={formFields.lastName}
            onChange={handleChange}
          />
        </div>

        <div className="buttonWraper">
          <button className="button" type="submit">
            {id ? "Atualizar usuário" : "Criar usuário"}
          </button>
          <Link to="/" className="button">
            Voltar
          </Link>
        </div>
      </form>
     
    </div>
  );
}

export default CreateUser;