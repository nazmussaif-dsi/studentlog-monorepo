import {useEffect, useState} from "react";
import styles from '../../styles/Home.module.css'
import Head from "next/head";
import RegistrationForm from "../../modules/teacher-registration/registration_form";
import {Divider} from "primereact/divider";


const axios = require('axios')
const teacher_application_api_address = "http://localhost:8080/teachers"

export default function NewTeacher() {
  const [applicationId, setApplicationId] = useState(null);

  useEffect(() => { // side effect hook
    //generating a blank form for getting a unique id for saving as draft
    const application_body = {
      appliedDate: new Date(),
      status: "draft"
    };
    axios.post(teacher_application_api_address, application_body).then(resp => {
      setApplicationId(resp.data.id);
    }).catch(error => {
      // console.log(error);
    });

  }, []);

  if(!applicationId){
    return <div>Loading...</div>
  }

  return (
    <>
      <div >
        <Head>
          <title>New Teacher Registration</title>
          <link rel="icon" href="../../public/favicon.ico"/>
        </Head>

        <main >
          <h1 className={styles.title}>
            Teacher Registration Form
          </h1>

          <Divider />

          {/*<RegistrationForm applicationId={applicationId}*/}
          {/*                 retrievedData={null}/>*/}

        </main>

        <footer className={styles.footer}>
        </footer>
      </div>
    </>

  );
}
