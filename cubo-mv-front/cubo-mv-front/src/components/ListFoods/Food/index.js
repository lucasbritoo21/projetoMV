import axios from 'axios';
import React, { useEffect, useState } from 'react';
import api, {URL} from '../../../services/api';
import './styles.css'
function Food({item, userId, cpf}) {
  const name = item && item.name;
  const verifyUserURL = item && item._links.user.href;
  const foodId = item && item._links.self.href.split("/").pop();
  console.log("ITEM", item);
  console.log("verifyUserURL", verifyUserURL)
  const [wasSelected, setWasSelected] = useState(false);
  const [isCurrentUser, setIsCurrentUser] = useState(false);
  // async (verifyUrl, userId) => await ;
  const selectedVerify = async () => {
    if (verifyUserURL) {
      console.log(verifyUserURL)
      try {
        const response = await axios.get(verifyUserURL);
        console.log("RESPONSE VERIFY", response.data.cpf === cpf);
        if (response.data.cpf === cpf) setIsCurrentUser(true);
        else setIsCurrentUser(false);
        setWasSelected(true);
      } catch (error) {
        setWasSelected(false);
      }
    }
  };
  useEffect(() => {
    selectedVerify();
  }, [cpf]);

  const handleSelected = async () => {
    if(verifyUserURL){
      const canEditSelected = wasSelected && isCurrentUser;
      if (canEditSelected) {
        try {
          const deleted = await api.delete(`/foods/${foodId}/user`)
          console.log("DELETADO", deleted);
          setWasSelected(false);
        } catch (error) {
          setWasSelected(true); 
        }
      } else if (!wasSelected) {
        try {
          const response = await axios({
            method: "put",
            url: verifyUserURL,
            headers: { "Content-Type": "text/uri-list" },
            data: `${URL}/users/${userId}`,
          });
          console.log("CLICKED", response);
          setWasSelected(true);
          setIsCurrentUser(true)
        } catch (error) {
          setWasSelected(false);
        }
      }
    }
  };

  return wasSelected ? (
    <div onClick={handleSelected} className="food--selected">
      {name}  {wasSelected && !isCurrentUser && <span className="span"> - Opcao selecionada por outro usuario</span>}
    </div>
  ) : (
    <div className="food" onClick={handleSelected}>
      {name}
    </div>
  );
}

export default Food;