import React, {useRef, useState} from "react";
import {InputText} from "primereact/inputtext";
import {Divider} from 'primereact/divider';
import {Button} from "primereact/button";
import {InputTextarea} from 'primereact/inputtextarea';
import {Calendar} from "primereact/calendar";
import {confirmDialog} from 'primereact/confirmdialog';
import {Toast} from 'primereact/toast';

const axios = require('axios')
const teacher_application_api_address = "http://localhost:8080/teachers"


export default function RegistrationForm() {
  const [name, setName] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [joiningDate, setJoiningDate] = useState("");
  const [highestEducationLevel, setHighestEducationLevel] = useState("");
  const [nationalRegistrationNo, setNationalRegistrationNo] = useState("");
  const [teacherId, setTeacherId] = useState("");
  const [designation, setDesignation] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [presentAddress, setPresentAddress] = useState("");
  const [permanentAddress, setPermanentAddress] = useState("");
  const [bloodGroup, setBloodGroup] = useState("");

  const applicationToast = useRef(null);

  const isValidName = name.match(/^(\w| ){5,50}$/);
  const isValidDateOfBirth = !isNaN(new Date(dateOfBirth).getTime());
  const isValidJoiningDate = !isNaN(new Date(joiningDate).getTime());
  const isValidHighestEducationLevel = highestEducationLevel.match(/^(\w| ){3,50}$/);
  const isValidNationalRegistrationNo = nationalRegistrationNo.match(/^[\d]{8,20}$/);
  const isValidTeacherId = teacherId.match(/^[\d]{4,20}$/);
  const isValidDesignation = designation.match(/^(\w| ){3,30}$/);
  const isValidContactNo = contactNo.match(/^(\+88)?01[0-9]{9}$/);
  const isValidPresentAddress = presentAddress.match(/^(\w| ){5,100}$/);
  const isValidPermanentAddress = permanentAddress.match(/^(\w| ){5,100}$/);
  const isValidBloodGroup = bloodGroup.match(/^(A|B|AB|O)[+-]$/);

  const isValidData = !!(
    isValidName
    && isValidDateOfBirth
    && isValidJoiningDate
    && isValidHighestEducationLevel
    && isValidNationalRegistrationNo
    && isValidTeacherId
    && isValidDesignation
    && isValidContactNo
    && isValidPresentAddress
    && isValidPermanentAddress
    && isValidBloodGroup
  );

  const clearData = () => {
    setName("");
    setDateOfBirth("");
    setJoiningDate("");
    setHighestEducationLevel("");
    setNationalRegistrationNo("");
    setTeacherId("");
    setDesignation("");
    setContactNo("");
    setPresentAddress("");
    setPermanentAddress("");
    setBloodGroup("");
  }

  const postStudentApplicationData = () => {
    let application_body = {
      name: name,
      dateOfBirth: dateOfBirth,
      joiningDate: joiningDate,
      highestEducationLevel: highestEducationLevel,
      nationalRegistrationNo: nationalRegistrationNo,
      teacherId: teacherId,
      designation: designation,
      contactNo: contactNo,
      presentAddress: presentAddress,
      permanentAddress: permanentAddress,
      bloodGroup: bloodGroup
    };

    axios.post(teacher_application_api_address, application_body).then(() => {
      applicationToast.current.show({
        severity: 'success',
        summary: 'Application Submitted Successfully',
        life: 3000
      });
      clearData();
    }).catch(() => {
      applicationToast.current.show({
        severity: 'error',
        summary: 'Error Occurred',
        detail: "Failed",
        life: 3000
      });
    });
  }

  const submitStudentApplicationData = (e) => {
    e.preventDefault();
    confirmDialog({
      message: "Submit the form?",
      header: 'Confirmation',
      icon: 'pi pi-save',
      accept: () => postStudentApplicationData()
    });
  }

  return (
    <React.Fragment>
      <Toast id="application_toast"
             ref={applicationToast}
             position="top-right"
      />

      <div className="p-field p-grid">
        <label htmlFor="name" className="p-col-fixed" style={{width: '180px'}}>Teacher's Name:</label>
        <div className="p-col">
          <InputText
            value={name}
            id="name"
            type="text"
            style={{width: '300px'}}
            onChange={event => setName(event.target.value)}/>
          {(name && !isValidName) && <small id="id-help" className="p-d-block p-error">
            Name must be between 5 to 50 characters.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="date_of_birth" className="p-col-fixed" style={{width: '180px'}}>Date of Birth:</label>
        <div className="p-col">
          <Calendar
            monthNavigator
            yearNavigator
            yearRange="1900:2100"
            dateFormat="dd-mm-yy"
            id="date_of_birth"
            value={new Date(dateOfBirth)}
            onChange={event => setDateOfBirth(event.target.value)}
          />
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="joining_date" className="p-col-fixed" style={{width: '180px'}}>Joining Date:</label>
        <div className="p-col">
          <Calendar
            monthNavigator
            yearNavigator
            yearRange="1900:2100"
            dateFormat="dd-mm-yy"
            id="joining_date"
            value={new Date(joiningDate)}
            onChange={event => setJoiningDate(event.target.value)}
          />
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="highest_education_level" className="p-col-fixed" style={{width: '180px'}}>Highest Education
          Level:</label>
        <div className="p-col">
          <InputText
            value={highestEducationLevel}
            id="highest_education_level"
            type="text"
            style={{width: '300px'}}
            onChange={event => setHighestEducationLevel(event.target.value)}/>
          {(highestEducationLevel && !isValidHighestEducationLevel) &&
          <small id="id-help" className="p-d-block p-error">
            Highest Education Level must be between 3 to 50 characters.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="national_registration_no" className="p-col-fixed" style={{width: '180px'}}>National Registration
          No:</label>
        <div className="p-col">
          <InputText
            value={nationalRegistrationNo}
            id="national_registration_no"
            type="text"
            style={{width: '300px'}}
            onChange={event => setNationalRegistrationNo(event.target.value)}/>
          {(nationalRegistrationNo && !isValidNationalRegistrationNo) &&
          <small id="id-help" className="p-d-block p-error">
            National Registration No must be between 8 to 20 digits.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="teacher_id" className="p-col-fixed" style={{width: '180px'}}>Teacher Id:</label>
        <div className="p-col">
          <InputText
            value={teacherId}
            id="teacher_id"
            type="text"
            style={{width: '300px'}}
            onChange={event => setTeacherId(event.target.value)}/>
          {(teacherId && !isValidTeacherId) && <small id="id-help" className="p-d-block p-error">
            Teacher Id must be between 4 to 20 digits.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="designation" className="p-col-fixed" style={{width: '180px'}}>Designation:</label>
        <div className="p-col">
          <InputText
            value={designation}
            id="designation"
            type="text"
            style={{width: '300px'}}
            onChange={event => setDesignation(event.target.value)}/>
          {(designation && !isValidDesignation) && <small id="id-help" className="p-d-block p-error">
            Designation must be between 3 to 30 digits.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="contact_no" className="p-col-fixed" style={{width: '180px'}}>Contact no:</label>
        <div className="p-col">
          <InputText
            value={contactNo}
            id="contact_no"
            type="text"
            style={{width: '300px'}}
            onChange={event => setContactNo(event.target.value)}/>
          {(contactNo && !isValidContactNo) && <small id="id-help" className="p-d-block p-error">
            Invalid Phone Number Entered.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="present_address" className="p-col-fixed" style={{width: '180px'}}>Present Address:</label>
        <div className="p-col">
          <InputTextarea
            value={presentAddress}
            id="present_address"
            type="text"
            style={{width: '300px'}}
            rows="2"
            onChange={event => setPresentAddress(event.target.value)}/>
          {(presentAddress && !isValidPresentAddress) && <small id="id-help" className="p-d-block p-error">
            Present Address must be between 5 to 100 characters.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="permanent_address" className="p-col-fixed" style={{width: '180px'}}>Permanent Address:</label>
        <div className="p-col">
          <InputTextarea
            value={permanentAddress}
            id="permanent_address"
            type="text"
            style={{width: '300px'}}
            rows="2"
            onChange={event => setPermanentAddress(event.target.value)}/>
          {(permanentAddress && !isValidPermanentAddress) && <small id="id-help" className="p-d-block p-error">
            Permanent Address must be between 5 to 100 characters.
          </small>}
        </div>
      </div>

      <div className="p-field p-grid">
        <label htmlFor="blood_group" className="p-col-fixed" style={{width: '180px'}}>Blood Group:</label>
        <div className="p-col">
          <InputText
            value={bloodGroup}
            id="blood_group"
            type="text"
            style={{width: '300px'}}
            onChange={event => setBloodGroup(event.target.value)}/>
          {(bloodGroup && !isValidBloodGroup) && <small id="id-help" className="p-d-block p-error">
            Invalid blood group.
          </small>}
        </div>
      </div>

      <Divider/>

      <div style={{margin: "auto", marginBottom: "1em"}}>
        <Button
          disabled={!isValidData}
          onClick={submitStudentApplicationData}
          icon="pi pi-check"
          className="p-button-primary p-mr-2"
          style={{width: '150px'}}
          label="Submit"
        />
      </div>
    </React.Fragment>
  );
}