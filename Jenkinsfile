pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                echo ("build the project")
            }
                }
        
         stage("Run unit test"){
            steps{
                echo("run UTs")
            }
        }
        
        stage("Deploy to dev"){
            steps{
                echo("deploy to dev done")
            }
        }
        
          
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
        
                
        stage('Run regression test cases on QA') {
            steps {
               echo("Run test cases on QA")
            }
        }
             
        stage("Deploy to stage"){
            steps{
                echo("deploy to stage done")
            }
        }  
                
        stage('Run sanity test cases on Stage') {
            steps {
               echo("Run sanity test cases on Stage")
            }
        }
        
          stage("Deploy to uat"){
            steps{
                echo("deploy to uat done")
            }
        }  
                
        stage('Run sanity test cases on uat') {
            steps {
               echo("Run sanity test cases on UAT")
            }
        }
        
      stage('Deploy to PROD') {
            steps {
               echo("deploy on PROD done")
            }
        }
     
    }
}