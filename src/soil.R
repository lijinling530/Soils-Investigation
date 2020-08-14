getwd()

# Regression Analysis - carbon
mydata=read.csv(file="soil.csv", head=TRUE, sep=",")
attach(mydata)
fit=lm(Carbon ~ Depth, data=mydata, na.action = na.exclude)
summary(fit)
plot(Carbon ~ Depth, data=mydata)
detach(mydata)

# Regression Analysis - nitrogen
mydata=read.csv(file="soil.csv", head=TRUE, sep=",")
attach(mydata)
fit=lm(Nitrogen ~ Depth, data=mydata, na.action = na.exclude)
summary(fit)
plot(Nitrogen ~ Depth, data=mydata)
detach(mydata)

# Pricipal Components Analysis
mydata = read.csv("work.csv") 
pca <- princomp(mydata, scores=TRUE, cor=TRUE)
summary(pca)
loadings(pca) 
plot(pca,type="lines")
biplot(pca)

# Cluster Analysis
mydata = read.csv("work.csv") 
trans=t(mydata)
d <- dist(trans, method = "euclidean")
clusr <- hclust(d, method="ward") 
plot(clusr)
groups <- cutree(clr, k=2) 
rect.hclust(clusr, k=2, border="red")  

# Correlation Analysis
mydata=read.csv(file="work.csv", head=TRUE, sep=",")
pairs(mydata)
corr=cor(mydata, use="complete.obs", method = "pearson") 
print(corr) 


