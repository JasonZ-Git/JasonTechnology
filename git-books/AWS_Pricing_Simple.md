# AWS Simple Pricing
AWS pricing model is rather complicated, a simplifed view is needed to look over Amazon's tricks of over charging.

AWS will begin the charging directly after 12 months' free, so stop the services if you don't use anymore to avoid any charge.
The instances are not the cheapest, but the one that could serve as a actual use as a Developer or a small application.
All the price are of USD.

## AWS instance

| Type     |  Details     |
| -------- | ------------ |
| t2.micro | 1vCPU, 1GB memory |
| t2.small | 1vCPU, 2GB memory |
| t3.micro | 2vCPU, 1GB memory |
| gp3      | General Purpose SSD |
| 1 ACU    | 2GB memory |


## AWS Simple Price - In progress

| AWS Service   |  Price       | Free tier |
| ------------- | ------------ | --------- |
| EC2 | reserved t2.small - $126/year |  12 months - t2.micro  |
| S3  | Standard storage <br/> - $0.023/GB-month Storage; <br/> - $0.005/1000 Put or list request <br/> - $0.0004/1000 Get requests | 12 months - 5GB standard |
| RDS | db.t3.micro reserved instance <br/> - instance $106/year <br/> - Storage $0.115/GB-month  | 12 months - t2.micro <br/> - 20G Storage, 20G backup or snapshot |
| Aurora(serveless) | $0.12/ACU Hour <br/> $0.10/GB-month Storage <br/> $0.20 per 1 million requests <br/> $0.2/G backup | - |
| API Gateway | $1/million Http API Call or $3.5/million Rest API Call | 12 months - 1 million call |
| Lambda | $0.06/GB-hour calculation <br/> $0.20/million requests | Always <br/> - 888 GB-hour/month calculation <br> - 1 million/month requests |
| SNS | $0.5/million API Request <br/> $2.00/100_000 email notification | Always <br/> - 1 million publish <br/> - 1000 email notification |
| SQS | $0.40/million requests | Always - 1 million requests |
| EBS | gp3 - $0.08/GB-month | |
| EFS | standard storage - $0.3/GB-month | 12 months - 5 GB standard storage |
| ECS | |
| ELB | |
| Route 53 |
| DynamoDB | |
| DAX | |
| CloudWatch | |
| ASG | |
| SES | |
| ElastiCache | |
| CloudFront | |
| Athena | |
| Global Accelerator | |
| Snow | |
| Storage Gateway | |
| FSx | |
| Kinesis | |
| Fargate | |
| ECR | |
| Cognito | |
| SSO | |
| Config | |
| WAF | |
| Shield | |
| Security Manager | |
| DataSync | |
| VPN Endpoint | |
| PrivateLink | |
| Organisations | |
| RAM | |
| KMS | |
| CloudHSM | |
| Direct Connect | |
| DMS | |
| SMS | |
| CloudFormation | |
| Step Function | |
| Trusted Advisor | |
| SSM | |