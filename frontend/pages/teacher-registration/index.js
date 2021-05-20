import Head from 'next/head'
import Link from 'next/link'
import styles from '../../styles/Home.module.css'
import { Button } from 'primereact/button';


export default function TeacherRegistrationHome() {
  return (
    <>
      <div className={styles.container}>
        <Head>
          <title>Teacher Registration</title>
          <link rel="icon" href="../../public/favicon.ico"/>
        </Head>

        <main className={styles.main}>
          <h1 className={styles.title}>
            Teacher Registration
          </h1>

          <h2>
            <p style={{textAlign: "center"}}>
              <Button className="p-button-link">
                <Link href ="/teacher-registration/new-teacher">
                  <a >Register New Teacher</a>
                </Link>
              </Button>
            </p>
          </h2>
        </main>

        <footer className={styles.footer}>
        </footer>
      </div>
    </>

  )
}
